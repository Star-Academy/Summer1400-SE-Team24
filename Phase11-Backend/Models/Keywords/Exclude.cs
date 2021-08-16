using System;
using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Models.Keywords
{
    public class Exclude : Keyword
    {
        private string word;

        public Exclude(string word)
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
                return new HashSet<Doc>();
            }
            if(newDocs != null)
                docs.ExceptWith(newDocs);
            return docs;
        }
    }
}