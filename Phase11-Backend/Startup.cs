using System;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Microsoft.EntityFrameworkCore;
using InvertedIndex.Data;
using InvertedIndex.Data.Services;
using InvertedIndex.Logic;

namespace InvertedIndex
{
    public class Startup
    {
        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
        }

        public IConfiguration Configuration { get; }

        public void ConfigureServices(IServiceCollection services)
        {

            services.AddControllers();

            string dbID = Environment.GetEnvironmentVariable("DB_ID");
            string dbPs = Environment.GetEnvironmentVariable("DB_PASSWORD");

            services.AddDbContext<AppDbContext>(options =>
            {
                options.UseSqlServer(
                    String.Format(
                    Configuration.GetConnectionString("defaultConnection")
                    , dbID, dbPs)
                );
            });

            services.AddScoped<IDocRepository, DocRepository>();
            services.AddScoped<SearchEngine, SearchEngine>();
            services.AddScoped<QueryParser, QueryParser>();
        }

        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }

            app.UseHttpsRedirection();

            app.UseRouting();

            app.UseCors(builder =>
            {
                builder.AllowAnyOrigin();
            });

            app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllers();
            });
        }
    }
}
