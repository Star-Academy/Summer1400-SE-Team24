using System.Collections.Generic;
namespace InvertedIndex.Keywords
{
    public inteface Keyword
    {
        public string getWord();
        public HashSet<Doc> operate(HashSet<Doc> docs, HashSet<Doc> newDocs);
    }
}