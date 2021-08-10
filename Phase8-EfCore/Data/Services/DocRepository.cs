using System.ComponentModel.Design;
using System.Linq;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;
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

        public IList<Doc> GetAllDocs() => _dbContext.Docs.ToList();

        public HashSet<Doc> GetDocs(string word) => 
            _dbContext.DocWordsAssigns.Where(a => a.Word.Text == word)
            .Include(a => a.Doc).Select(a => a.Doc).ToHashSet();

        public void AssignDocToWords(string docID, IList<string> words)
        {
            this.AddDoc(docID);

            foreach (var word in words)
            {
                this.AddWord(word);

                _dbContext.DocWordsAssigns.Add(new DocWordAssign()
                {
                    DocID = docID,
                    WordID = _dbContext.Words.SingleOrDefault(w => w.Text == word).WordID
                });
            }
        }

        public void AddDoc(string docID)
        {
            if(!_dbContext.Docs.Any(d => d.DocID == docID))
            {
                _dbContext.Docs.Add(new Doc()
                {
                    DocID = docID
                });
            }
        }

        public void AddWord(string word)
        {
            if(!_dbContext.Words.Any(w => w.Text == word))
            {
                _dbContext.Words.Add(new Word()
                {
                    Text = word
                });
            }
        }
        public bool IsEmpty() => !_dbContext.Docs.Any();

        public void Save()
        {
            _dbContext.SaveChanges();
        }
    }
}