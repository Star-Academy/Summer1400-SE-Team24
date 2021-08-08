using System;
using System.Collections.Generic;

namespace InvertedIndex.FileHandler
{
    public class Doc
    {
        private string docName;
        private IList<string> words;

        public Doc(string docName, IList<string> words)
        {
            this.docName = docName;
            this.words = words;
        }

        public IList<string> getWords()
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
            Doc doc = (Doc)obj;
            return this.docName.Equals(doc.docName);
        }
        
        public override int GetHashCode()
        {
            return this.docName.GetHashCode();
        }

        public override string ToString()
        {
            return $"{this.docName}";
        }
    }
}