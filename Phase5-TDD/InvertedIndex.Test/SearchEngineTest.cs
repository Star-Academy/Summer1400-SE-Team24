using System.Linq;
using System.Collections.Generic;
using Xunit;
using Moq;
using InvertedIndex.Search;
using InvertedIndex.FileHandler;
using InvertedIndex.Keywords;

namespace InvertedIndex.Test
{
    public class SearchEngineTest 
    {
        private readonly Doc DOC1;
        private readonly Doc DOC2;
        private readonly Doc DOC3;

        Mock<QueryParser> _parserMock;
        Mock<Search.InvertedIndex> _indexMock;
        Mock<Keyword> _ordinaryMock;
        Mock<Keyword> _unionMock;
        Mock<Keyword> _excludeMock;

        SearchEngine _engine;

        public SearchEngineTest()
        {
            DOC1 = new Doc("57110", new List<string>());
            DOC2 = new Doc("59483", new List<string>());
            DOC3 = new Doc("59519", new List<string>());

            _indexMock = new Mock<Search.InvertedIndex>();
            _indexMock.Setup(i => i.Get(It.IsAny<string>())).Verifiable();
            _engine = new SearchEngine(_indexMock.Object);

            _ordinaryMock = new Mock<Keyword>();
            _excludeMock = new Mock<Keyword>();
            _unionMock = new Mock<Keyword>();
            _ordinaryMock.Setup( o => o.GetWord()).Verifiable();
            _excludeMock.Setup( o => o.GetWord()).Verifiable();
            _unionMock.Setup( o => o.GetWord()).Verifiable();
        }

        private HashSet<Doc> buildHashSet(bool doc1Exists, bool doc2Exists, bool doc3Exists)
        {
            var set = new HashSet<Doc>();
            if(doc1Exists) set.Add(DOC1);
            if(doc2Exists) set.Add(DOC2);
            if(doc3Exists) set.Add(DOC3);

            return set;
        }

        private HashSet<Doc> mockDocs(IDictionary<Mock<Keyword>,bool[]> keywordDocs)
        {

            var keywordsMock = keywordDocs.Keys.Select(k => k.Object).ToList();
            _parserMock = new Mock<QueryParser>();
            _parserMock.Setup(p => p.ParseQuery(It.IsAny<string>())).Returns(keywordsMock);

            foreach(var pair in keywordDocs)
            {
                var includeArray = pair.Value;
                pair.Key.Setup(k => k.Operate(It.IsAny<HashSet<Doc>>(), It.IsAny<HashSet<Doc>>()))
                    .Returns(buildHashSet(includeArray[0], includeArray[1], includeArray[2]));
            }

            return _engine.Search(_parserMock.Object.ParseQuery(""));
        }

        [Fact]
        public void unionWithExcludeTest()
        {
            var keywordDocs = new Dictionary<Mock<Keyword>,bool[]>();
            keywordDocs.Add(_ordinaryMock, new bool[] {false,true,false});
            keywordDocs.Add(_unionMock, new bool[] {true,true,false});
            keywordDocs.Add(_excludeMock, new bool[] {false,true,false});
            
            var docs = mockDocs(keywordDocs);
            var expectedDocs = buildHashSet(false,true,false);
            Assert.Equal(expectedDocs, docs);
        }

        [Fact]
        public void includeAllTest()
        {
            var keywordDocs = new Dictionary<Mock<Keyword>,bool[]>();

            keywordDocs.Add(_ordinaryMock, new bool[] {false,false,false});
            keywordDocs.Add(_unionMock, new bool[] {true,true,true});
            
            
            var docs = mockDocs(keywordDocs);
            var expectedDocs = buildHashSet(true,true,true);
            Assert.Equal(expectedDocs, docs);
        }

        [Fact]
        public void excludeAllTest()
        {    
            var keywordDocs = new Dictionary<Mock<Keyword>,bool[]>();
            keywordDocs.Add(_ordinaryMock, new bool[] {true,false,true});
            keywordDocs.Add(_excludeMock, new bool[] {false,false,false});
            
            var docs = mockDocs(keywordDocs);
            var expectedDocs = buildHashSet(false,false,false);
            Assert.Equal(expectedDocs, docs);
        }

        [Fact]
        public void simpleSearchTets()
        {
            var keywordDocs = new Dictionary<Mock<Keyword>,bool[]>();

            keywordDocs.Add(_ordinaryMock, new bool[] {true,true,false});

            
            var docs = mockDocs(keywordDocs);
            var expectedDocs = buildHashSet(true,true,false);
            Assert.Equal(expectedDocs, docs);
        }
    }
}