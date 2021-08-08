using System.Collections.Generic;
using InvertedIndex.FileHandler;
using Xunit;
using InvertedIndex.Search;
using System.IO;

namespace InvertedIndex.Test
{
    public class SearchTest 
    {
        private FileInfo[] testFiles;
        private IList<Doc> testDocs;

        public SearchTest(){
            IDirectoryReader reader = new DirectoryReader();
            testFiles = reader.GetFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            FileReader fileReader = new FileReader();
            this.testDocs = fileReader.getFilesDocs(testFiles);
        }

        [Fact]
        public void SearchEngineTest() {
            var index = new InvertedIndex.Search.InvertedIndex(testDocs, new Mapper());
            var engine = new SearchEngine(index);

            string query = "i have -friend";
            var parser = new QueryParser();
            var docs = engine.search(parser.ParseQuery(query));
            var expectedDocs = new HashSet<Doc>() {
                    new Doc("59496", new List<string>()),
                    new Doc("59495", new List<string>()),
                    new Doc("59494", new List<string>())
            };

            Assert.Equal(expectedDocs, docs);
        }
    }
}