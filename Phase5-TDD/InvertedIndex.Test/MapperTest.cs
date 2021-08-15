using System;
using System.Collections.Generic;
using Xunit;
using InvertedIndex.Search;
using InvertedIndex.FileHandler;

namespace InvertedIndex.Test
{
    public class MapperTest : IDisposable
    {
        private const string WORD1 = "word1";
        private const string WORD2 = "word2";
        private const string WORD3 = "word3";

        private readonly Doc DOC_TEST1;
        private readonly Doc DOC_TEST2;

        private HashSet<Doc> _set1;
        private HashSet<Doc> _set2;
        private HashSet<Doc> _set12;

        private Mapper _mapper;

        public MapperTest()
        {
            DOC_TEST1 = new Doc("1", new List<string> () {WORD1, WORD2, WORD3});
            DOC_TEST2 = new Doc("2", new List<string>());

            _set1 = new HashSet<Doc>();
            _set2 = new HashSet<Doc>();
            _set12 = new HashSet<Doc>();

            _set1.Add(DOC_TEST1);
            _set2.Add(DOC_TEST2);
            _set12.Add(DOC_TEST1);
            _set12.Add(DOC_TEST2);

            _mapper = new Mapper();
        }

        [Fact]
        public void MergeMapsTest()
        {
            var map1 = new Dictionary<string, HashSet<Doc>>();
            var map2 = new Dictionary<string, HashSet<Doc>>();
            var map3 = new Dictionary<string, HashSet<Doc>>();

            map1.Add(WORD1, _set1);
            map1.Add(WORD2, _set2);

            map2.Add(WORD1, _set2);
            map2.Add(WORD3, _set1);

            map3.Add(WORD1, _set12);
            map3.Add(WORD2, _set2);
            map3.Add(WORD3, _set1);

            var result = _mapper.MergeMaps(map1, map2);
            
            Assert.Equal(map3, result);
        }

        [Fact]
        public void GetDocMapTest()
        {
            var map = _mapper.GetDocMap(DOC_TEST1);

            Assert.Equal(map[WORD1], _set1);
        }

        public void Dispose()
        {
            GC.Collect();
        }
    }
}