using System.IO;
using System;
using InvertedIndex.Logic;
using InvertedIndex.Data.Services;

namespace InvertedIndex
{
    class Program
    {
        static readonly string docsPath = Path.GetFullPath("./docs");
        static void Main(string[] args)
        {
            IDocRepository docHandler = new DocRepository();

            if(docHandler.IsEmpty())
            {
                var fileReader = new FileReader(docHandler);
                fileReader.AddFilesToDataBase(docsPath);
            }

            var engine = new SearchEngine(docHandler);

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
