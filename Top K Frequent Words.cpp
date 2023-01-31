class Solution{
public:
    vector<string> topKFrequent(vector<string>& words,int k){
        auto compare=[] (pair<string, int> &x,pair<string, int>&y)
        {
           
            return x.second==y.second ? x.first.compare(y.first)<0: x.second>y.second;
        };
        vector<string> toReturn(k);
        map<string, int>occur;
        priority_queue<pair<string, int>, vector<pair<string, int>>,decltype(compare)>minHeap(compare);
         for(string word: words){
             occur[word]++;
         }
         for(auto &freq:occur){
             if(minHeap.size() <k){
                 minHeap.push({freq.first, freq.second});
             }else{
                 if(freq.second > minHeap.top().second){
                     minHeap.pop();
                     minHeap.push({freq.first,freq.second});
                 }
             }
         }
         int index=k-1;
         while(!minHeap.empty()){
             toReturn[index--] =minHeap.top().first;
             minHeap.pop();

         }
         return toReturn;

    }
};