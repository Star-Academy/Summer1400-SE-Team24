using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public interface Keyword
    {
        public string GetWord();
        public HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs);
    }
}