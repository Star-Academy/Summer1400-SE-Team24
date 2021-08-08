using System.IO;
using System.Collections.Generic;
using Xunit;
using Moq;
using InvertedIndex.Search;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Test
{
    public class InvertedIndexTest
    {
        private Search.InvertedIndex _index;
        private IDictionary<string, HashSet<Doc>> _mockMap;
        private HashSet<Doc> _docSet;
        private Mock<Mapper> _mockMapper;

        private void initMockings(string mockKey, IList<string> docNames)
        {
            _docSet = new HashSet<Doc>();

            foreach(var name in docNames)
            {
                _docSet.Add(new Doc(name, new List<string>()));
            }

            _mockMap = new Dictionary<string, HashSet<Doc>>();
            _mockMap.Add(mockKey, _docSet);

            _mockMapper = new Mock<Mapper>();
            _mockMapper.Setup(m => m.mergeMaps(
                It.IsAny<IDictionary<string,HashSet<Doc>>>(), It.IsAny<IDictionary<string,HashSet<Doc>>>()
                )).Returns(_mockMap);

            _index = new Search.InvertedIndex(new List<Doc>() 
            {
                new Doc("1", new List<string>())
            }, _mockMapper.Object);
        }

        [Fact]
        public void InvertedIndexGetTest1()
        {
            const string KEY = "key";
            initMockings(KEY, new List<string>() {"1", "2", "3"});

            var result = _index.Get(KEY);
            Assert.Equal(_docSet, result);
        }

        [Fact]
        public void InvertedIndexGetTest2()
        {
            const string KEY= "anotherKey";
            initMockings(KEY, new List<string>());

            var result = _index.Get(KEY);
            Assert.Equal(_docSet, result);
        }
    }
}