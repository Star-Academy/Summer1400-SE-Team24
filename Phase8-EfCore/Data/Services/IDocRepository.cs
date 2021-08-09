using System.Collections.Generic;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Data.Services
{
    public interface IDocRepository
    {
        IList<Doc> GetAllDocs();
        void AddDoc(string docName, string docText);
    }
}