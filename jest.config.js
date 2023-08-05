module.exports = {
  preset: 'ts-jest',
  testEnvironment: 'node',
  testTimeout: 10000, // 10 seconds timeout for each test
  testMatch: ['**/__tests__/**/*.ts?(x)', '**/?(*.)+(spec|test).ts?(x)'],
  transform: {
    '^.+\\.tsx?$': 'babel-jest',
  },
};
