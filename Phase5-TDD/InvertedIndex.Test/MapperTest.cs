using System.Collections.Generic;
using InvertedIndex.FileHandler;
using Xunit;
using InvertedIndex.Search;

namespace InvertedIndex.Test
{
    public class MapperTest
    {
        [Fact]
        public void getDocMapTest(){
            var mapper = new Mapper();
            var doc = new Doc("1", new List<string>(){"word1", "word2", "word3"});
            var map = mapper.getDocMap(doc);
            Assert.Equal(map["word1"], new HashSet<Doc>(){doc});
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
    }
}