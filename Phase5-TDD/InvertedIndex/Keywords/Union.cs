using System.Globalization;
using System;
using System.Collections.Generic;

namespace InvertedIndex.Keywords
{
    public class Union : Keywords
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
        public override HashSet<Doc> operate(HashSet<Doc> docs, HashSet<Doc> newDocs)
        {
            throw new NotImplementedException();
        }
    }
}