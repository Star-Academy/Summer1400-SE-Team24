using System.Collections.Generic;
using InvertedIndex.Data.Entities;
using InvertedIndex.Models.Keywords;

namespace InvertedIndex.Logic
{
    public class SearchEngine
    {
        private Models.InvertedIndex index;

        public SearchEngine(Models.InvertedIndex index)
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