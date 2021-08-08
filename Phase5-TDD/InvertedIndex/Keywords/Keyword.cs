using System.Collections.Generic;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Keywords
{
    public interface Keyword
    {
        string GetWord();
        HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs);
    }
}