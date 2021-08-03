using System;
using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public class Ordinary : Keyword
    {
        private string word;

        public Ordinary(string word)
        {
            this.word = word;
        }

        public string GetWord()
        {
            return word;
        }
        public HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
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