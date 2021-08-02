using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public interface Keyword
    {
        public string getWord();
        public HashSet<Doc> operate(HashSet<Doc> docs, HashSet<Doc> newDocs);
    }
}