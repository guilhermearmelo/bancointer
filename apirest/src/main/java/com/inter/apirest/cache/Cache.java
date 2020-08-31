package com.inter.apirest.cache;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.inter.apirest.dto.DigitoUnicoDto;
import com.inter.apirest.dto.EntradaDigitoUnico;


public class Cache {
	
	public static final Map<EntradaDigitoUnico, CacheItem> MEM_CACHE = new HashMap<>();
	
	public static int SEQUENCE_CACHE = 0;
	
	public static final int SIZE_CACHE = 10;
	
	public static int menorIdade = 999999999;
	
	public void insert(DigitoUnicoDto digitoUnicoDto) {
		EntradaDigitoUnico entradaDigitoUnico = new EntradaDigitoUnico();
		entradaDigitoUnico.setN(digitoUnicoDto.getN());
		entradaDigitoUnico.setK(digitoUnicoDto.getK());
		
		boolean contemNaCache = false;
		for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
        	if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
        		contemNaCache = true;
        	}
        }
		
		
		// verifica se a cache está cheia
		if(MEM_CACHE.size() < SIZE_CACHE) {
			// verifica se a cache contém o que procuramos
			if(contemNaCache) {
				// renova o registro na cache
				int idade=0;
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
		        		idade = entry.getValue().getIdadeItem();
		        	}
		        }
				
				CacheItem cacheItem = new CacheItem(digitoUnicoDto.getResultado(), SEQUENCE_CACHE);
				
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
		        		entry.setValue(cacheItem);
		        	}
		        }
				
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getValue().getIdadeItem()<idade) {
						entry.getValue().setIdadeItem(entry.getValue().getIdadeItem()+1);
		        	}
		        }
				SEQUENCE_CACHE++;
			} else {
				// insere novo registro na cache
				CacheItem cacheItem = new CacheItem(digitoUnicoDto.getResultado(), SEQUENCE_CACHE);
				
				MEM_CACHE.put(entradaDigitoUnico, cacheItem);
				SEQUENCE_CACHE++;
			}
		} else { // se estiver cheia
			if(contemNaCache) {
				// renova o registro na cache
				int idade=0;
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
		        		idade = entry.getValue().getIdadeItem();
		        	}
		        }
				
				CacheItem cacheItem = new CacheItem(digitoUnicoDto.getResultado(), SEQUENCE_CACHE);
				
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
		        		entry.setValue(cacheItem);
		        	}
		        }
				
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getValue().getIdadeItem()<idade) {
						entry.getValue().setIdadeItem(entry.getValue().getIdadeItem()+1);
		        	}
		        }
				SEQUENCE_CACHE++;
			} else {
				// adiciona o novo registro na cache e apaga o registro mais antigo
				EntradaDigitoUnico chave = new EntradaDigitoUnico();
				CacheItem cacheItem = new CacheItem(digitoUnicoDto.getResultado(), SEQUENCE_CACHE);
				
				for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
					if(entry.getValue().getIdadeItem()<menorIdade) {
						menorIdade = entry.getValue().getIdadeItem();
						//cacheItem.setResultado(entry.getValue().getResultado());
						chave.setK(entry.getKey().getK());
						chave.setN(entry.getKey().getN());
					}
				}
				
				Iterator<EntradaDigitoUnico> iterator = MEM_CACHE.keySet().iterator();
				while(iterator.hasNext()){
					EntradaDigitoUnico aux = new EntradaDigitoUnico();
					aux = iterator.next();
					if(aux.getK()==chave.getK() && aux.getN().compareTo(chave.getN()) == 0) {
						iterator.remove();
					}
				}
				
				MEM_CACHE.put(entradaDigitoUnico, cacheItem);
				SEQUENCE_CACHE++;
				menorIdade = 999999999;
			}
		}
	}
	
	public int search(EntradaDigitoUnico entradaDigitoUnico) {
		 
        for (Map.Entry<EntradaDigitoUnico,CacheItem> entry : MEM_CACHE.entrySet()) {
        	if(entry.getKey().getK()==entradaDigitoUnico.getK() && entry.getKey().getN().compareTo(entradaDigitoUnico.getN())==0   ) {
        		return entry.getValue().getResultado();
        	}
        }
		return -1;
	}
	
	
}
