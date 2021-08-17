using System;
using InvertedIndex.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace InvertedIndex.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext(DbContextOptions options) : base(options)
        {}

        public DbSet<Doc> Docs { get; set; }
        public DbSet<Word> Words { get; set; }
        public DbSet<DocWordAssign> DocWordsAssigns { get; set; }
    }
}