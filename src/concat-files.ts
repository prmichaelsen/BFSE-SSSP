#!/usr/bin/env ts-node

import fs from 'fs';
import path from 'path';
import yargs from 'yargs';
import fastGlob from 'fast-glob';

function readFilesRecursiveSync(
  directory: string,
  ignoreHidden: boolean,
  includePaths: string[],
  excludePaths: string[],
  outputFileName: string
): string[] {
  const fileContents: string[] = [];

  const files = fs.readdirSync(directory);

  const filteredFiles = ignoreHidden ? files.filter(file => !file.startsWith('.')) : files;

  filteredFiles.forEach((file: string) => {
    const filePath = path.join(directory, file);
    const relativeFilePath = path.relative(directory, filePath);

    // Check if the current file is the output file, if yes, then skip it
    if (path.resolve(filePath) === path.resolve(outputFileName)) {
      return;
    }

    const isIncluded = includePaths.some(pattern => fastGlob.sync(pattern, { cwd: directory }).includes(relativeFilePath));
    const isExcluded = excludePaths.some(pattern => fastGlob.sync(pattern, { cwd: directory }).includes(relativeFilePath));

    if (isIncluded && !isExcluded) {
      const stats = fs.statSync(filePath);
      if (stats.isDirectory()) {
        const contents = readFilesRecursiveSync(directory, ignoreHidden, includePaths, excludePaths, outputFileName);
        fileContents.push(...contents);
      } else if (stats.isFile()) {
        const data = fs.readFileSync(filePath, 'utf8');
        fileContents.push(`=== ${relativeFilePath} ===\n${data}\n`);
      }
    }
  });

  return fileContents;
}


async function concatenateFilesWithNames(
  directory: string,
  outputFileName: string,
  ignoreHidden: boolean,
  includePaths: string[],
  excludePaths: string[]
) {
  try {
    const fileContents = readFilesRecursiveSync(directory, ignoreHidden, includePaths, excludePaths, outputFileName);
    const outputData = fileContents.join('');

    fs.writeFile(outputFileName, outputData, { encoding: 'utf8', flag: 'w' }, (err: NodeJS.ErrnoException | null) => {
      if (err) {
        console.error(`Error writing to output file ${outputFileName}: ${err}`);
      } else {
        console.log(`Concatenated files successfully and saved to ${outputFileName}.`);
      }
    });
  } catch (error) {
    console.error(error);
  }
}

const argv: any = yargs
  .usage('Usage: $0 --dir [directory] --output [output-file]')
  .option('dir', {
    alias: 'd',
    describe: 'Target directory for reading files recursively',
    demandOption: true,
  })
  .option('output', {
    alias: 'o',
    describe: 'Output file path',
    default: 'output.txt',
  })
  .option('ignoreHidden', {
    alias: 'i',
    describe: 'Ignore hidden files (files whose names start with a dot)',
    boolean: true,
    default: false,
  })
  .option('includePaths', {
    alias: 'n',
    describe: 'Include specific file paths using glob syntax (comma-separated)',
    array: true,
    default: [],
  })
  .option('excludePaths', {
    alias: 'e',
    describe: 'Exclude specific file paths using glob syntax (comma-separated)',
    array: true,
    default: [],
  })
  .argv;

const directoryPath = argv.dir;
const outputFileName = argv.output;
const ignoreHidden = argv.ignoreHidden;
const includePaths = argv.includePaths;
const excludePaths = argv.excludePaths;
concatenateFilesWithNames(directoryPath, outputFileName, ignoreHidden, includePaths, excludePaths);
