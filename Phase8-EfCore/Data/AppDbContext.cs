using InvertedIndex.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace InvertedIndex.Data
{
    public class AppDbContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            base.OnConfiguring(builder);
            
            builder.UseSqlServer(
                "Server=.;Database=InvertedIndex_Db;Integrated_security=true;"
            );
        }

        public DbSet<Doc> Docs { get; set; }
        public DbSet<Word> Words { get; set; }
        public DbSet<DocWordAssign> DocWordsAssigns { get; set; }
    }
}