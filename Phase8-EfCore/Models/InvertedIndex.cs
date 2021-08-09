using System.Collections.Generic;
using System;
using InvertedIndex.Data.Entities;
using InvertedIndex.Logic;

namespace InvertedIndex.Models
{
    public class InvertedIndex
    {
        private IDictionary<string, HashSet<Doc>> map;

        public InvertedIndex(IList<Doc> docs, Mapper mapper)
        {
            map = new Dictionary<string, HashSet<Doc>>();

            foreach (var doc in docs) 
                map = mapper.MergeMaps(map, mapper.GetDocMap(doc));
        }

        public virtual HashSet<Doc> Get(string word)
        {
            return map[word];
        }
    }
}