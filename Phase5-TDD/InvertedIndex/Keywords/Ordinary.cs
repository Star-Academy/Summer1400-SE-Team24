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