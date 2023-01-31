class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (needs == null || price == null) return 0;
        return shopping(price, special, needs, new HashMap<List<Integer>, Integer>());
    }
    
    private int shopping(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> map) {
        if (map.containsKey(needs)) {
            return map.get(needs);
        }
        List<Integer> res = new ArrayList<>();
        // buy all needed items without special offer
        res.add(multiply(price, needs));
        
        for (int i = 0; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            if (checkNeeds(offer, needs)) {
                List<Integer> clone = new ArrayList<>(needs);
                for (int j = 0; j < clone.size(); j++) {
                    clone.set(j, clone.get(j) - offer.get(j));
                }
                res.add(offer.get(offer.size() - 1) + shopping(price, special, clone, map));
            }
        }
        
        int ret = Integer.MAX_VALUE;
        for (int cost : res) {
            ret = Math.min(ret, cost);
        }
        
        map.put(needs, ret);
        return ret;
    }
    
    private int multiply(List<Integer> price, List<Integer> needs) {
        int ret = 0;
        for (int i = 0; i < needs.size(); i++) {
            ret += price.get(i) * needs.get(i);
        }
        
        return ret;
    }
    
    private boolean checkNeeds(List<Integer> offer, List<Integer> needs) {
        boolean ret = true;
        for (int i = 0; i < needs.size(); i++) {
            ret &= offer.get(i) <= needs.get(i);
        }
        
        return ret;
    }
}
