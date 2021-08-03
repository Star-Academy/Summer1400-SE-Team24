using System.Collections.Generic;
using InvertedIndex.Keywords;
using InvertedIndex.FileHandler;
using Xunit;
using InvertedIndex.Search;
using System.IO;
using InvertedIndex.Exceptions;
using System;
using System.Linq;

namespace InvertedIndex.Test
{
    public class SearchTest 
    {
        private FileInfo[] testFiles;
        private IList<Doc> testDocs;

        public SearchTest(){
            IDirectoryReader reader = new DirectoryReader();
            testFiles = reader.getFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            FileReader fileReader = new FileReader();
            this.testDocs = fileReader.getFilesDocs(testFiles);
        }

        [Fact]
        public void fileReadingTest() {
            IDirectoryReader reader = new DirectoryReader();
            var files = reader.getFiles("/media/adib/Local Disk3/Projects/Star-Academy/Summer1400-SE-Team24/Phase5-TDD/InvertedIndex.Test/docs");
            Assert.Equal(files.Length, 8);
        }

        [Fact]
        public void docEqualsTest() {
            string sameName = "sameName";
            var content = new List<string>();
            Doc doc1 = new Doc(sameName, content);
            Doc doc2 = new Doc(sameName, content);

            Assert.Equal(doc1, doc2);
        }

        [Fact]
        public void getFilesDocsTest() {
            Assert.Equal(testFiles.Length, testDocs.Count);
            Assert.Equal(testDocs[0].getName(), testFiles[0].Name);
            Assert.Equal(testDocs[0].getWords().Count, 8);
        }

        [Fact]
        public void invertedIndexGetTest() {
            var index = new InvertedIndex.Search.InvertedIndex(testDocs, new Mapper());
            var result = index.get("have");
            Assert.Equal(result.Count, 5);
        }

        [Fact]
        public void mergeMapsTest() {

            var mapper = new Mapper();
            var map1 = new Dictionary<string, HashSet<Doc>>();
            var map2 = new Dictionary<string, HashSet<Doc>>();
            var map3 = new Dictionary<string, HashSet<Doc>>();

            map1.Add("word1", new HashSet<Doc>() {new Doc("1", new List<string>())});
            map1.Add("word2", new HashSet<Doc>() {new Doc("2", new List<string>())});

            map2.Add("word1", new HashSet<Doc>() {new Doc("2", new List<string>())});
            map2.Add("word3", new HashSet<Doc>() {new Doc("1", new List<string>())});

            map3.Add("word1", new HashSet<Doc>() {new Doc("1", new List<string>()), new Doc("2", new List<string>())});
            map3.Add("word2", new HashSet<Doc>() {new Doc("2", new List<string>())});
            map3.Add("word3", new HashSet<Doc>() {new Doc("1", new List<string>())});

            var result = mapper.mergeMaps(map1, map2);

            Assert.Equal(map3, result);
        }

        [Fact]
        public void parseQueryTest() {
            var parser = new QueryParser();
            string query = "-exclude ordinary +union";
            var keywords = parser.parseQuery(query);

            Assert.Equal(keywords[0].GetWord(), "ordinary");
            Assert.Equal(keywords[1].GetWord(), "union");
            Assert.Equal(keywords[2].GetWord(), "exclude");
        }

        [Fact]
        public void getDocMapTest(){
            var mapper = new Mapper();
            var doc = new Doc("1", new List<string>(){"word1", "word2", "word3"});
            var map = mapper.getDocMap(doc);
            Assert.Equal(map["word1"], new HashSet<Doc>(){doc});
        }


        [Fact]
        public void SearchEngineTest() {
            var index = new InvertedIndex.Search.InvertedIndex(testDocs, new Mapper());
            var engine = new SearchEngine(index);

            string query = "i have -friend";
            var parser = new QueryParser();
            var docs = engine.search(parser.parseQuery(query));
            var expectedDocs = new HashSet<Doc>() {
                    new Doc("59496", new List<string>()),
                    new Doc("59495", new List<string>()),
                    new Doc("59494", new List<string>())
            };

            Assert.Equal(expectedDocs, docs);
        }
    }
}