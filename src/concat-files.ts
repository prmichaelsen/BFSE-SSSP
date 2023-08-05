#!/usr/bin/env ts-node

import fs from 'fs';
import path from 'path';
import yargs from 'yargs';

async function readFilesRecursive(directory: string): Promise<string[]> {
  const fileContents: string[] = [];

  return new Promise<string[]>((resolve, reject) => {
    fs.readdir(directory, (err: NodeJS.ErrnoException | null, files: string[]) => {
      if (err) {
        reject(`Error reading directory ${directory}: ${err}`);
        return;
      }

      const promises: Promise<void>[] = files.map((file: string) => {
        const filePath = path.join(directory, file);
        return new Promise<void>((resolve, reject) => {
          fs.stat(filePath, (err: NodeJS.ErrnoException | null, stats: fs.Stats) => {
            if (err) {
              reject(`Error getting stats for file ${filePath}: ${err}`);
              return;
            }

            if (stats.isDirectory()) {
              readFilesRecursive(filePath)
                .then((contents) => {
                  fileContents.push(...contents);
                  resolve();
                })
                .catch(reject);
            } else if (stats.isFile()) {
              fs.readFile(filePath, 'utf8', (err: NodeJS.ErrnoException | null, data: string) => {
                if (err) {
                  reject(`Error reading file ${filePath}: ${err}`);
                  return;
                }

                fileContents.push(`=== ${filePath} ===\n${data}\n`);
                resolve();
              });
            }
          });
        });
      });

      Promise.all(promises)
        .then(() => resolve(fileContents))
        .catch(reject);
    });
  });
}

async function concatenateFilesWithNames(directory: string, outputFileName: string) {
  try {
    const fileContents = await readFilesRecursive(directory);
    const outputData = fileContents.join('');
    fs.writeFile(outputFileName, outputData, 'utf8', (err: NodeJS.ErrnoException | null) => {
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

const argv = yargs
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
  .argv;

const directoryPath = (argv as any).dir;
const outputFileName = (argv as any).output;
concatenateFilesWithNames(directoryPath, outputFileName);
