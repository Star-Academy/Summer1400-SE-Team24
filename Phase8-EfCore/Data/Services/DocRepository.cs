using System.Collections.Generic;
using System.Linq;
using InvertedIndex.Data.Entities;

namespace InvertedIndex.Data.Services
{
    public class DocRepository : IDocRepository
    {
        private readonly AppDbContext _dbContext;

        public DocRepository()
        {
            _dbContext = new AppDbContext();
        }

        public IList<Doc> GetAllDocs()
        {
            return _dbContext.Docs.ToList();
        }

        public void AddDoc(string docName, string docText)
        {
            _dbContext.Docs.Add(new Doc()
            {
                ID = docName,
                Text = docText
            });
        }

        public void Save()
        {
            _dbContext.SaveChanges();
        }
    }
}