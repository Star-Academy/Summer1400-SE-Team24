using System;
using InvertedIndex.Logic;
using InvertedIndex.Data.Services;

namespace InvertedIndex
{
    class Program
    {
        static void Main(string[] args)
        {
            IDocRepository docHandler = new DocRepository();

            var index = new Models.InvertedIndex(docHandler.GetAllDocs(), new Mapper());
            var engine = new SearchEngine(index);

            Console.Write("Enter Query: ");
            string query = Console.ReadLine();

            var parser = new QueryParser();
            var resultDocs = engine.Search(parser.ParseQuery(query));

            foreach (var doc in resultDocs)
            {
                Console.WriteLine(doc);
            }
        }
    }
}
