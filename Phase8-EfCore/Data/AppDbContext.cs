using System;
using InvertedIndex.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace InvertedIndex.Data
{
    public class AppDbContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            base.OnConfiguring(builder);
            
            string dbID = Environment.GetEnvironmentVariable("DB_ID");
            string dbPs = Environment.GetEnvironmentVariable("DB_PASSWORD");

            builder.UseSqlServer(
                $"Server=.;Database=InvertedIndex_Db;User Id={dbID};Password={dbPs};"
            );
        }

        public DbSet<Doc> Docs { get; set; }
        public DbSet<Word> Words { get; set; }
        public DbSet<DocWordAssign> DocWordsAssigns { get; set; }
    }
}