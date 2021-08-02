using System.Collections.Generic;
using System;
using System.Collections;

namespace InvertedIndex.Search
{
    public class InvertedIndex
    {
        private IDictionary<string, HashSet<Doc>> map;

        public InvertedIndex(IList<Doc> docs, Mapper mapper)
        {
            throw new NotImplementedException();
        }

        public HashSet<Doc> get(string word)
        {
            throw new NotImplementedException();
        }
    }
}