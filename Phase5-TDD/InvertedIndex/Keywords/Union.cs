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