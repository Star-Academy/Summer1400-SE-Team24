using System;
using System.Globalization;

namespace InvertedIndex.FileHandler
{
    public class Doc
    {
        private string docName;
        private IList<string> words;

        public Doc(string docName, List<string> words)
        {
            this.docName = docName;
            this.words = words;
        }

        public List<string> getWords()
        {
            return words;
        }

        public string getName()
        {
            return docName;
        }

        public override bool Equals(object obj)
        {
            
            if (obj == null || GetType() != obj.GetType())
            {
                return false;
            }
            
            throw new NotImplementedException();
        }
        
        public override int GetHashCode()
        {
            throw new NotImplementedException();
        }

        public override ToString()
        {
            throw new NotImplementedException();
        }
    }
}