using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Data.Services
{
    public interface IDocRepository
    {
        IList<Doc> GetAllDocs();
        IList<Doc> GetDocs(string word);
        void AddDoc(string docName, string docText);
        bool IsEmpty();
        void Save();
    }
}