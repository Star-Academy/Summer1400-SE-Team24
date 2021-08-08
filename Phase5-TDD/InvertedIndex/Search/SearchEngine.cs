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
        public HashSet<Doc> search(IList<Keyword> keywords)
        {
            HashSet<Doc> docs = null;

            foreach(var keyword in keywords) {
                
                var newDocs = index.get(keyword.GetWord());
                docs = keyword.Operate(docs, newDocs);
            }
            return docs;
        }
    }
}