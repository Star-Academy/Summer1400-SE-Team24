using System;
using System.Collections.Generic;

namespace InvertedIndex.FileHandler
{
    public class Doc
    {
        private string _docName;
        private IList<string> _words;

        public Doc(string docName, IList<string> words)
        {
            this._docName = docName;
            this._words = words;
        }

        public IList<string> GetWords()
        {
            return _words;
        }

        public string GetName()
        {
            return _docName;
        }

        public override bool Equals(object obj)
        {
            
            if (obj == null || GetType() != obj.GetType())
            {
                return false;
            }
            Doc doc = (Doc)obj;
            return this._docName.Equals(doc._docName);
        }
        
        public override int GetHashCode()
        {
            return this._docName.GetHashCode();
        }

        public override string ToString()
        {
            return $"{this._docName}";
        }
    }
}