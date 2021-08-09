using InvertedIndex.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace InvertedIndex.Data
{
    public class AppDbContext : DbContext
    {
        public AppDbContext() {}

        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlServer(
                "server=.;database=InvertedIndex_Db;trusted_connection=true;"
            );
        }

        public DbSet<Doc> Docs { get; }
    }
}