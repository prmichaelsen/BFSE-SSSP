interface Edge {
  weight: number;
  initial: number;
  terminal: number;
  step: number;
}

export class BFSE {
  private readonly INF = 0x0FFFFFFF;
  private readonly NEG_INF = -268435455;
  private adjList: Array<Array<Edge | null>>;
  private distance: number[];
  private parent: number[];
  private factor: number;
  private absoluteMinimum: number;

  constructor() {
    this.adjList = [];
    this.distance = [];
    this.parent = [];
    this.factor = 0;
    this.absoluteMinimum = 0;
  }

  addEdge(edge: Edge): void {
    const { initial, terminal, weight } = edge;

    if (this.factor === 0) {
      this.factor = weight;
    } else if (this.factor > weight) {
      this.factor = weight;
    }

    if (weight < 0) {
      this.absoluteMinimum += weight;
    }

    if (initial + 1 > this.adjList.length) {
      const amountToAdd = initial + 1 - this.adjList.length;
      this.adjList.push(...Array(amountToAdd).fill(null));
      this.distance.push(...Array(amountToAdd).fill(this.INF));
      this.parent.push(...Array(amountToAdd).fill(-1));
    }

    if (terminal + 1 > this.adjList.length) {
      const amountToAdd = terminal + 1 - this.adjList.length;
      this.adjList.push(...Array(amountToAdd).fill(null));
      this.distance.push(...Array(amountToAdd).fill(this.INF));
      this.parent.push(...Array(amountToAdd).fill(-1));
    }

    if (!this.adjList[initial]) {
      this.adjList[initial] = [];
    }

    this.adjList[initial].push(edge);
  }

  addEdgeWithParams(weight: number, initial: number, terminal: number): void {
    this.addEdge({ weight, initial, terminal, step: 0 });
  }

  sssp(source: number): number[] {
    const ssspParent = [...this.parent];
    const ssspDistance = [...this.distance];
    const ssspAdjList: any = this.adjList.map((edges) => (edges ? [...edges] : null));
    const queue: Array<number | Edge> = [];

    ssspDistance[source] = 0;
    queue.push(source);

    while (queue.length > 0) {
      const item = queue.shift()!;
      let initial: number;
      let expanded: Edge;

      if (typeof item === 'number') {
        initial = item;
        const edges = ssspAdjList[initial];

        if (edges) {
          for (const edge of edges) {
            this.step(edge, queue, ssspParent, ssspDistance);
          }
        }
      } else {
        expanded = item;
        this.step(expanded, queue, ssspParent, ssspDistance);
      }
    }

    console.log('Naïve Gabow:');
    console.log('Distances:', ssspDistance);
    console.log('Parents:', ssspParent);
    console.log();

    return ssspDistance;
  }

  bfsssp(source: number): number[] {
    const ssspParent = [...this.parent];
    const ssspDistance = [...this.distance];
    const ssspAdjList = this.adjList.map((edges) => (edges ? [...edges] : null));

    ssspDistance[source] = 0;

    for (let i = 0; i < ssspAdjList.length; i++) {
      const edges = ssspAdjList[i];

      if (edges) {
        for (let j = 0; j < edges.length; j++) {
          const edge = edges[j]!;
          const { weight, initial, terminal } = edge;

          if (ssspDistance[terminal] > ssspDistance[initial] + weight) {
            ssspDistance[terminal] = ssspDistance[initial] + weight;
            ssspParent[terminal] = initial;
          }
        }
      }
    }

    console.log('Bellman-Ford:');
    console.log('Distances:', ssspDistance);
    console.log('Parents:', ssspParent);
    console.log();

    return ssspDistance;
  }

  step(edge: Edge, queue: Array<number | Edge>, ssspParent: number[], ssspDistance: number[]): void {
    const { weight, initial, terminal, step } = edge;

    if (step > this.factor) {
      edge.step = step - 1;
      queue.push(edge);
    } else if (ssspDistance[terminal] > ssspDistance[initial] + weight) {
      if (ssspDistance[terminal] < this.absoluteMinimum) {
        ssspDistance[terminal] = this.NEG_INF;
      } else {
        ssspDistance[terminal] = ssspDistance[initial] + weight;
      }

      ssspParent[terminal] = initial;
      queue.push(terminal);
    }
  }

  toString(): string {
    return 'BFSE [adjList=' + JSON.stringify(this.adjList) + ']';
  }
}

// Example usage:
const bfs = new BFSE();
bfs.addEdgeWithParams(5, 0, 1);
bfs.addEdgeWithParams(-2, 1, 2);
bfs.addEdgeWithParams(10, 0, 2);
bfs.addEdgeWithParams(3, 2, 3);
bfs.sssp(0);
bfs.bfsssp(0);