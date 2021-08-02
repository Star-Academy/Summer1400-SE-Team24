using System;
using System.Collections.Generic;

namespace InvertedIndex.Keywords
{
    public class Ordinary : Keywords
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
        public override HashSet<Doc> operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
        {
            throw new NotImplementedException();
        }
    }
}