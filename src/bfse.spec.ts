import { BFSE } from './bfse'; // Assuming you've exported BFSE class from the previous code

describe('BFSE', () => {
  let bfs = new BFSE();

  beforeEach(() => {
    bfs = new BFSE();
  });

  test('should calculate shortest path using sssp', () => {
    bfs.addEdgeWithParams(5, 0, 1);
    bfs.addEdgeWithParams(-2, 1, 2);
    bfs.addEdgeWithParams(10, 0, 2);
    bfs.addEdgeWithParams(3, 2, 3);

    const distances = bfs.sssp(0);

    expect(distances).toEqual([0, 5, 3, 6]);
  });

  test('should calculate shortest path using bfsssp', () => {
    bfs.addEdgeWithParams(5, 0, 1);
    bfs.addEdgeWithParams(-2, 1, 2);
    bfs.addEdgeWithParams(10, 0, 2);
    bfs.addEdgeWithParams(3, 2, 3);

    const distances = bfs.bfsssp(0);

    expect(distances).toEqual([0, 5, 3, 6]);
  });

  test('should handle negative cycles using sssp', () => {
    bfs.addEdgeWithParams(5, 0, 1);
    bfs.addEdgeWithParams(-2, 1, 2);
    bfs.addEdgeWithParams(10, 0, 2);
    bfs.addEdgeWithParams(-15, 2, 1); // Negative cycle

    const distances = bfs.sssp(0);

    expect(distances).toEqual([0, 5, -2, -1]);
  });

  test('should handle negative cycles using bfsssp', () => {
    bfs.addEdgeWithParams(5, 0, 1);
    bfs.addEdgeWithParams(-2, 1, 2);
    bfs.addEdgeWithParams(10, 0, 2);
    bfs.addEdgeWithParams(-15, 2, 1); // Negative cycle

    const distances = bfs.bfsssp(0);

    expect(distances).toEqual([0, 5, -2, -1]);
  });
});
