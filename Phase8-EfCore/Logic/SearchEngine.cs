using System.Collections.Generic;
using InvertedIndex.Data.Entities;
using InvertedIndex.Models.Keywords;

namespace InvertedIndex.Logic
{
    public class SearchEngine
    {
        private IDocRepository _docManager;

        public SearchEngine(IDocRepository docManager)
        {
            this._docManager = docManager;
        }
        public HashSet<Doc> Search(IList<Keyword> keywords)
        {
            HashSet<Doc> docs = null;

            foreach(var keyword in keywords) {
                
                var newDocs = _docManager.GetDocs(keyword.GetWord());
                docs = keyword.Operate(docs, newDocs);
            }
            return docs;
        }
    }
}