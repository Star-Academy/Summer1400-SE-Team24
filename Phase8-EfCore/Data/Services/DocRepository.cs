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
            _dbContext.Docs
            .Include(d => d.DocWordAssign).ThenInclude(a => a.Word)
            .Where(d => d.DocWordAssign.Word.Text == word).ToHashSet();

        public void AddDocWithWords(string docID, IList<string> words)
        {
            this.AddDoc(docID);

            int wordId;
            foreach (var word in words)
            {
                wordId = this.AddWord(word);

                _dbContext.DocWordsAssigns.Add(new DocWordAssign()
                {
                    DocID = docID,
                    WordID = wordId
                });
            }
        }

        public void AddDoc(string docID)
        {
            _dbContext.Docs.Add(new Doc()
            {
                DocID = docID
            });
        }

        public int AddWord(string word)
        {
            var addedWord = _dbContext.Words.Add(new Word()
            {
                Text = word
            });

            return addedWord.Entity.WordID;
        }
        public bool IsEmpty() => !_dbContext.Docs.Any();

        public void Save()
        {
            _dbContext.SaveChanges();
        }
    }
}