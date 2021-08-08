using System.Collections.Generic;
using System;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Search
{
    public class InvertedIndex
    {
        private IDictionary<string, HashSet<Doc>> map;

        public InvertedIndex(IList<Doc> docs, Mapper mapper)
        {
            map = new Dictionary<string, HashSet<Doc>>();

            foreach (var doc in docs) 
                map = mapper.mergeMaps(map, mapper.getDocMap(doc));
        }

        public HashSet<Doc> Get(string word)
        {
            return map[word];
        }
    }
}