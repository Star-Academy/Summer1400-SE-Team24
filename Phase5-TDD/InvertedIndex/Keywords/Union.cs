using System.Globalization;
using System;
using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public class Union : Keyword
    {
        private string word;

        public Union(string word)
        {
            this.word = word;
        }

        public virtual string GetWord()
        {
            return word;
        }
        public virtual HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
        {
            if(docs == null) {
            return newDocs;
            }
            if(newDocs != null)
                docs.UnionWith(newDocs);
            return docs;
        }
    }
}