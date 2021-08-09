using InvertedIndex.Data.Entities;
using Microsoft.EntityFrameworkCore;

namespace InvertedIndex.Data
{
    public class AppDbContext : DbContext
    {
        protected override void OnConfiguring(DbContextOptionsBuilder builder)
        {
            builder.UseSqlServer(
                "server=localhost;database=InvertedIndex_Db;User Id=SA;Password=M_e1415926536;"
            );
        }

        public DbSet<Doc> Docs { get; }
    }
}