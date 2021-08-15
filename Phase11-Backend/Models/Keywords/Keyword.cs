using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Models.Keywords
{
    public interface Keyword
    {
        string GetWord();
        HashSet<Doc> Operate(HashSet<Doc> docs, HashSet<Doc> newDocs);
    }
}