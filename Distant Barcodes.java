class Solution {
    public int[] rearrangeBarcodes(int[] barcodes){
        int n= barcodes.length;
        int[] res= new int[n];
        Map<Integer,Integer>map =new HashMap<>();
        for(int b:barcodes){
            map.put(b,map.getOrDefault(b,0) +1);
        }
        PriorityQueue<Integer> maxHeap =new PriorityQueue<>((a,b) ->map.get(b) -map.get(a));
        maxHeap.addAll(map.keySet());

        int index=0;
        while(maxHeap.size()>0){
            int a=maxHeap.poll();
            res[index++]=a;
            if(maxHeap.size()==0) break;
            int b=maxHeap.poll();
            res[index++]=b;
            update(map, maxHeap, a);
            update(map,maxHeap, b);
        }
        return res;
    }
    private void update(Map<Integer, Integer>map,PriorityQueue<Integer> maxHeap, int a){
        if(map.get(a) ==1){
            map.remove(a);
        }
        else{
            map.put(a, map.get(a) - 1);
            maxHeap.offer(a);
        }
    }
}