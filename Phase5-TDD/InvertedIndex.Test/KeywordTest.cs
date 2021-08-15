using System;
using System.Collections.Generic;
using InvertedIndex.Keywords;
using InvertedIndex.FileHandler;
using Xunit;

namespace InvertedIndex.Test
{
    public class KeywordTest : IDisposable
    {
        private Keyword _keyword;
        private HashSet<Doc> _docs, _newDocs, _expectedResult;
        private readonly IList<string> SAMPLE_WORDS = new List<string>() {"some", "words"};
        private readonly Doc DOC_TEST1;
        private readonly Doc DOC_TEST2;
        private readonly Doc DOC_TEST3;
        private const string DUMMY_WORD = "dummyWord";

        private void initDocs()
        {
            _docs = new HashSet<Doc>();
            _docs.Add(DOC_TEST1);
            _docs.Add(DOC_TEST2);

            _newDocs = new HashSet<Doc>();
            _newDocs.Add(DOC_TEST1);
            _newDocs.Add(DOC_TEST3);
        }

        public KeywordTest()
        {
            DOC_TEST1 = new Doc("1", SAMPLE_WORDS);
            DOC_TEST2 = new Doc("2", SAMPLE_WORDS);
            DOC_TEST3 = new Doc("3", SAMPLE_WORDS);

            _expectedResult = new HashSet<Doc>();

            initDocs();
        }

        [Fact]
        public void OrdinaryTest()
        {
            _keyword = new Ordinary(DUMMY_WORD);
            _expectedResult.Add(DOC_TEST1);
            var result = _keyword.Operate(_docs, _newDocs);

            Assert.Equal(_expectedResult, result);
        }

        [Fact]
        public void ExcludeTest()
        {
            _keyword = new Exclude(DUMMY_WORD);
            _expectedResult.Add(DOC_TEST2);
            var result = _keyword.Operate(_docs, _newDocs);

            Assert.Equal(_expectedResult, result);
        }

        [Fact]
        public void UnionTest()
        {
            _keyword = new Union(DUMMY_WORD);
            _expectedResult.Add(DOC_TEST1);
            _expectedResult.Add(DOC_TEST2);
            _expectedResult.Add(DOC_TEST3);
            var result = _keyword.Operate(_docs, _newDocs);

            Assert.Equal(_expectedResult, result);
        }

        public void Dispose()
        {
            GC.Collect();
        }
    }
}