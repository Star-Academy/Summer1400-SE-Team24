using System;
using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public class Exclude : Keyword
    {
        private string word;

        public Exclude(string word)
        {
            this.word = word;
        }

        public string getWord()
        {
            return word;
        }
        public HashSet<Doc> operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
        {
            throw new NotImplementedException();
        }
    }
}