package com.brennaswitzer.aoc;

import java.util.*;

public class Register {
    
    List<Opcode> instructions = new ArrayList<>();
    Histogram h = new Histogram();
    int count = 0;
    
    /**
     * create an array of potential opcodes to run through, List<Opcodes> codes
     * create Map of opcodes that each have a set of potentials
     * count the number of keys in your map that have at least three items
     */
    
    void addOpcode(int id, int A, int B, int C, int[] before, int[] after) {
        Opcode o = new Opcode(id, A, B, C, before, after);
        instructions.add(o);
    }
    
    int countThrees() {
        Set<Integer> keys = h.keySet();
        int count = 0;
        for(Integer k : keys) {
            if(h.get(k).size() >= 3) {
                count++;
            }
        }
        return count;
    }
    
    public int countInstructions() {
        EnumSet<Operation> op = EnumSet.allOf(Operation.class);
        int total = 0;
        
        for (Opcode i : instructions) {
            this.count = 0;
            op.forEach(operation -> {
                int[] result = getOperation(operation, i);
                if (Arrays.equals(result, i.after)) {
                    this.count++;
                }
            });
            if(count >= 3) {
                total++;
            }
        }
        return total;
    }
    
    int[] getOperation(Operation operation, Opcode i) {
        switch (operation) {
            case ADDI:
                return addi(i);
            case ADDR:
                return addr(i);
            case MULR:
                return mulr(i);
            case MULI:
                return muli(i);
            case BANR:
                return banr(i);
            case BANI:
                return bani(i);
            case BORR:
                return borr(i);
            case BORI:
                return bori(i);
            case SETR:
                return setr(i);
            case SETI:
                return seti(i);
            case GTIR:
                return gtir(i);
            case GTRI:
                return gtri(i);
            case GTRR:
                return gtrr(i);
            case EQIR:
                return eqir(i);
            case EQRI:
                return eqri(i);
            case EQRR:
                return eqrr(i);
            default:
                return null;
        }
    }
    
    /**
     * addr (add register) stores into register C the result of adding register A and register B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] addr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] + i.before[i.B];
        return result;
    }
    
    /**
     * addi (add immediate) stores into register C the result of adding register A and value B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] addi(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] + i.B;
        return result;
    }
    
    /**
     * mulr (multiply register) stores into register C the result of multiplying register A and register B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] mulr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] * i.before[i.B];
        return result;
    }
    
    /**
     * muli (multiply register) stores into register C the result of multiplying register A and value B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] muli(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] * i.B;
        return result;
    }
    
    /**
     * banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] banr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] & i.before[i.B];
        return result;
    }
    
    /**
     * bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] bani(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] & i.B;
        return result;
    }
    
    /**
     * borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] borr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] ^ i.before[i.B];
        return result;
    }
    
    /**
     * bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
     * @param i Opcode
     * @return int[] register
     */
    public int[] bori(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A] ^ i.B;
        return result;
    }
    
    /**
     * setr (set register) copies the contents of register A into register C. (Input B is ignored.)
     * @param i Opcode
     * @return int[] register
     */
    public int[] setr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.before[i.A];
        return result;
    }
    
    /**
     * seti (set immediate) stores value A into register C. (Input B is ignored.)
     * @param i Opcode
     * @return int[] register
     */
    public int[] seti(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        result[i.C] = i.A;
        return result;
    }
    
    /**
     * gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtir(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.A > i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }
    
    /**
     * gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtri(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.before[i.A] > i.B) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }

    /**
     * gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] gtrr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.before[i.A] > i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }

    /**
     * eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqir(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.A == i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }

    /**
     * eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqri(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.before[i.A] == i.B) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }

    /**
     * eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
     * @param i Opcode
     * @return int[] register
     */
    public int[] eqrr(Opcode i) {
        int[] result = Arrays.copyOf(i.before, i.before.length);
        if(i.before[i.A] == i.before[i.B]) {
            result[i.C] = 1;
        } else {
            result[i.C] = 0;
        }
        return result;
    }

    
    public static class Opcode {
        int id;
        int A;
        int B;
        int C;
        int[] before;
        int[] after;
        
        Opcode(int id, int A, int B, int C, int[] before, int[] after) {
            this.id = id;
            this.A = A;
            this.B = B;
            this.C = C;
            this.before = before;
            this.after = after;
        }
        
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Before: " + before);
            sb.append("[" + id + "," + A + "," + B + "," + C + "]");
            sb.append("After: " + after);
            return sb.toString();
        }
    }
    
    public enum Operation {
        ADDR, ADDI, MULR, MULI, BANR, BANI, BORR, BORI, SETR, SETI, GTIR, GTRI, GTRR, EQIR, EQRI, EQRR
    }
}
