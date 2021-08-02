using System.Collections.Generic;
using System;
using System.Collections;

namespace InvertedIndex.Search
{
    public class Mapper
    {
        private InvertedIndex index;

        public SearchEngine(InvertedIndex index)
        {
            this.index = index;
        }
        public HashSet<Doc> search(IList<Keyword> keywords)
        {
            throw new NotImplementedException();
        }
    }
}