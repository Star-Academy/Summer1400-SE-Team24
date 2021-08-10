using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Data.Services
{
    public interface IDocRepository
    {
        IList<Doc> GetAllDocs();
        HashSet<Doc> GetDocs(string word);
        void AddDocWithWords(string docName, IList<string> words);
        bool IsEmpty();
        void Save();
    }
}