using System.Collections.Generic;
using System;
using InvertedIndex.Keywords;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Search
{
    public class SearchEngine
    {
        private InvertedIndex index;

        public SearchEngine(InvertedIndex index)
        {
            this.index = index;
        }
        public HashSet<Doc> Search(IList<Keyword> keywords)
        {
            HashSet<Doc> docs = null;

            foreach(var keyword in keywords) {
                
                var newDocs = index.Get(keyword.GetWord());
                docs = keyword.Operate(docs, newDocs);
            }
            return docs;
        }
    }
}