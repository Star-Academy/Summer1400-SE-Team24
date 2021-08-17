using System;
using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Models.Keywords
{
    public class Ordinary : Keyword
    {
        private string word;

        public Ordinary(string word)
        {
            this.word = word;
        }

        public virtual string GetWord()
        {
            return word;
        }
        public virtual HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
        {
            if(docs == null){
                return newDocs;
            }
            if(newDocs != null){
                docs.IntersectWith(newDocs);
            }
            return docs;
        }
    }
}